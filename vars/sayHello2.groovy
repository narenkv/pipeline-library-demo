#!/usr/bin/env groovy

import hudson.model.User
import hudson.security.Permission

def call(String name = 'human') {
  echo "Hello, ${name}."
}
