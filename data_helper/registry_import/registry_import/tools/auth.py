import json
import requests

from halo import Halo

from config import config


# Authenticate against keycloak.
def keycloak_auth(username, password, keycloak_url):
    spinner = Halo(text=f'Authenticating as [{username}]...', spinner='dots')
    spinner.start()

    print(keycloak_url)

    try:
        response = requests.post(keycloak_url,
                                data = {'client_id': 'admin-cli',
                                        'grant_type': 'password',
                                        'username': username,
                                        'password': password})
    except:
        spinner.fail(f'Error: {response.status_code}')

    response_json = response.json()
    access_token = response_json['access_token']

    spinner.succeed(f'OK: {response.status_code}')

    config['access_token'] = access_token
