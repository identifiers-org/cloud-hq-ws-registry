#!/usr/bin/env python3

import argparse
import configparser
import os
import sys
import urllib
import requests

from halo import Halo

from config import config

from data_fixer import (
    create_bad_pool,
    create_institution_pool,
    map_location,
    map_institution,
    populate_locations
)

from tools import fetch_old_data, load_mirid, load_old_data, prepare_resource, do_post
from tools import init_config, init_args, load_countries
from tools import keycloak_auth

from classes import Institution, Location, Namespace, Provider, Requester

from data_fixer import mint_used_ids


# Inits config and argument parser.
dirname = os.path.dirname(__file__)
init_config(os.path.join(dirname, 'config/config.ini'))

print(config)

args = init_args()


# Gets authentication token if auth parameter is set.
if args.auth is not None:
    access_token = keycloak_auth(args.auth[0], args.auth[1], config['keycloak_url'])


# Fetches data from old identifiers.org.
if args.datafile is None:
    namespaces = fetch_old_data(config['DataOriginURL'])
else:
    namespaces = load_old_data(args.datafile)


# Gets country list.
countries = load_countries(os.path.join(dirname, '../data/ISO-3166.csv'))

if args.skiplocations is False:
    # Populate country list.
    populate_locations(countries)


# Main loop:
if args.skipnamespaces is False:
    for index, namespace in enumerate(namespaces):
        spinner = Halo(spinner='dots')
        spinner.info(f'Working on namespace [{index}]: \"{namespace["name"]}\"')

        newNamespace = Namespace(prefix=namespace['prefix'].strip(),
                                 mirId=namespace['id'].strip(),
                                 name=namespace['name'].strip(),
                                 pattern=namespace['pattern'].strip(),
                                 description=namespace['definition'].strip(),
                                 sampleId=namespace['resources'][0]['localId'].strip(),
                                 namespaceEmbeddedInLui=(namespace['prefixed'] == 1))

        # Post the namespace.
        namespaceRef = prepare_resource('namespaces',
                                        namespace['prefix'],
                                        'findByPrefix',
                                        newNamespace.serialize())

        # Mint id if parameter is set.
        if args.mintidsfornamespaces is True:
            load_mirid(namespace['id'])

        # Post providers for that namespace.
        for provider in namespace['resources']:

            # Post location for that provider. This should never post locations anymore,
            # as they are all added with populate_locations.
            if provider.get('location') is not None:
                location = map_location(provider['location'].strip(), countries)[0]

                locationRef = prepare_resource('locations',
                                            location['countryCode'],
                                            'findByCountryCode',
                                            location)

            # Post institution for that resource.
            if provider.get('institution') is not None:
                newInstitution = Institution(name=provider['institution'].strip(),
                                            homeUrl=config['EMPTY_FIELD_LITERAL'],
                                            description=config['EMPTY_FIELD_LITERAL'],
                                            location=locationRef)

                institutionRef = prepare_resource('institutions',
                                                provider['institution'].strip(),
                                                'findByName',
                                                newInstitution.serialize())

            # Post the provider.
            newProvider = Provider(mirId=provider.get('id').strip(),
                                urlPattern=provider.get('accessURL', config['EMPTY_FIELD_LITERAL']).strip(),
                                name=provider.get('info', config['EMPTY_FIELD_LITERAL']).strip(),
                                description=provider.get('info', config['EMPTY_FIELD_LITERAL']).strip(),
                                official=provider.get('official', 'false'),
                                providerCode=provider.get('resourcePrefix', config['EMPTY_FIELD_LITERAL']).strip(),
                                sampleId=provider.get('localId', config['EMPTY_FIELD_LITERAL']).strip(),
                                resourceHomeUrl=provider.get('resourceURL', config['EMPTY_FIELD_LITERAL']).strip(),
                                location=locationRef,
                                institution=institutionRef,
                                namespace=namespaceRef)

            do_post(newProvider.serialize(), newProvider.name, 'resources', True)

        spinner = Halo(spinner='dots', text_color='green')
        spinner.succeed(f'Namespace [{index}]: \"{newNamespace.name}\" → [SUCCESS]')
        print()


# Populate mir ids if parameter is set.
if args.miriam is True:
    mint_used_ids(os.path.join(dirname, '../data/mirids.txt'), mirid_controller_url)
