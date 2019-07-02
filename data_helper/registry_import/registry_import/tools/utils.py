import argparse
import configparser
import csv
import os

import pandas as pd

from halo import Halo

from config import config


def init_config(config_file_name):
  spinner = Halo(text='Initializing config', spinner='dots')
  spinner.start()

  # Decides where are we working on.
  env = os.getenv('ENV') or 'DEV'

  spinner.info(f'Using {env} environment.')

  # Fetches config.
  config_file_contents = configparser.ConfigParser()
  config_file_contents.read_file(open(config_file_name))

  config['data_origin_url'] = config_file_contents.get('DEFAULT', 'DataOriginURL')
  config['destination_url'] = config_file_contents.get(env, 'DestinationURL')
  config['keycloak_url'] = config_file_contents.get(env, 'KeycloakURL')
  config['mirid_controller_url'] = config_file_contents.get(env, 'mirIDControllerURL')
  config['EMPTY_FIELD_LITERAL'] = config_file_contents.get(env, 'EmptyFieldLiteral')


def init_args():
  # Argument parser.
  parser = argparse.ArgumentParser(description='Populates repositories of Identifiers.org cloud.')
  parser.add_argument('-f', '--datafile', action='store', help='Use a data file instead of fetching.')
  parser.add_argument('-s', '--skiperror', action='store_true', help='Continue on error.')
  parser.add_argument('-v', '--verbose', action='store_true', help='Show detailed output.')
  parser.add_argument('-m', '--miriam', action='store_true', help='Also populate old miriams in database.')
  parser.add_argument('-z', '--mintidsfornamespaces', action='store_true', help='Populate miriams for imported namespaces.')
  parser.add_argument('-n', '--skipnamespaces', action='store_true', help='Skip namespace population (use along -m).')
  parser.add_argument('-l', '--skiplocations', action='store_true', help='Skip location population.')
  parser.add_argument('-a', '--auth', action='store', nargs=2, help='Authenticate using the given username and password.')

  return parser.parse_args()


def load_countries(country_csv_file_name):
  spinner = Halo(text='Loading ISO-3166 country list...', spinner='dots')
  spinner.start()

  # Load countries from ISO-3166 listing.
  countries = pd.read_csv(country_csv_file_name,
                             sep=',',
                             header=0,
                             quotechar='"',
                             skipinitialspace = True,
                             names=['countryName', 'countryCode', 'Alpha-3 code'],
                             usecols=[0, 1, 2],
                             keep_default_na=False)

  spinner.succeed(f'Got {len(countries)} countries.')

  return countries






  # # Prepare csv reader and skip header.
  #   csv_reader = csv.reader(country_csv, delimiter=',', quotechar='"')
  #   next(csv_reader, None)

  #   country_list = {rows[1]:rows[0] for rows in csv_reader}