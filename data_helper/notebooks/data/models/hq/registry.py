# HQ Registry Data Model
# Author: Manuel Bernal Llinares <mbdebian@gmail.com>

class Institution:
    def __init__(self):
        self._id = None
        self.name = None
        self.description = None


class Location:
    def __init__(self):
        self._id = None
        self.countryCode = None


class NamespaceSynonym:
    def __init__(self):
        self._id
        self.__synonym = None
        # DBRef
        self.namespace = None

    @property
    def synonym(self):
        return self.__synonym

    @synonym.setter
    def synonym(self, value):
        self.__synonym = value
        self._id = self.__synonym


class Namespace:
    def __init__(self):
        self._id = None
        self.prefix = None
        self.mirId = None
        self.name = None
        self.pattern = None
        self.description = None
        self.created = None
        self.modified = None
        self.deprecated = None
        self.deprecationDate = None
        # DBRef
        self.resources = []
        # DBRef
        self.namespaceSynonmyns = []


class Resource:
    def __init__(self):
        self.id = None
        self.mirId = None
        self.accessUrl = None
        self.info = None
        self.official = None
        self.resourcePrefix = None
        self.localId = None
        self.resourceUrl = None
        # DBRef
        self.institution = None
        # DBRef
        self.location = None
        # DBRef
        self.namespace = None
