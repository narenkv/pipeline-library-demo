#!/usr/bin/env groovy

import hudson.model.User
import hudson.security.Permission

def call(String name = 'human') {
  
  def inst = Jenkins.getInstanceOrNull()
  def strategy = inst.getAuthorizationStrategy()
  strategy.getMetaPropertyValues().find { it.getName() == 'grantedPermissions'}.each {
      // it.value is a map with permission as key and the corresponding users as value
      print(it.value[Permission.READ])
      print(it.value[Permission.WRITE])
      print(it.value[Permission.HUDSON_ADMINISTER])
  }
  
}
