#!/usr/bin/env groovy

import hudson.model.User
import hudson.security.Permission

import hudson.model.*
  
import jenkins.model.*

import hudson.security.*

import com.cloudbees.plugins.*

import hudson.scm.*



def call(String name = 'human') {
  
  def inst = Jenkins.getInstanceOrNull()
  def strategy = inst.getAuthorizationStrategy()
  strategy.getMetaPropertyValues().find { it.getName() == 'grantedPermissions'}.each {
      // it.value is a map with permission as key and the corresponding users as value
      print(it.value[Permission.READ])
      print(it.value[Permission.WRITE])
      print(it.value[Permission.HUDSON_ADMINISTER])
  }
  
  def job = Jenkins.getInstance().getItemByFullName(env.JOB_BASE_NAME, Job.class)
  def build = job.getBuildByNumber(env.BUILD_ID as int)
  def userId = build.getCause(Cause.UserIdCause).getUserId()
  def user = User.current()
  println(" ----------------- Output 2 ---------------");
  println(user);
  println(userId);
  println(job);
   
  def user2 = currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
   println(user2);
  
  
 def proj = Hudson.instance.getItem("script-job")
  //AuthorizationMatrixProperty authProperty = proj.getProperty(hudson.security.AuthorizationMatrixProperty)
  
  
  def jen = Jenkins.getInstance();
  def jobToAddPermissions = "script-job"
  def jobToUpdate = jen.getItem(jobToAddPermissions);
  def autToUpdate = jobToUpdate.getProperty(AuthorizationMatrixProperty.class)
  def currPermissionSet = autToUpdate.getGrantedPermissions();
  
   Map<hudson.security.Permission,Set<String>>  gp = autToUpdate.getGrantedPermissions();
   println(gp);
  
   gp.each{ us ->
          println(us);
          println("hello ---> ")
     
   }
  //GlobalMatrixAuthorizationStrategy auth = new GlobalMatrixAuthorizationStrategy();
  
  //println(currPermissionSet );
}
