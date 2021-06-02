#!/usr/bin/env groovy

import hudson.model.User
import hudson.security.Permission

import hudson.model.*




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
  
  
  AbstractProject proj = Hudson.instance.getItem("script")
  //AuthorizationMatrixProperty authProperty = proj.getProperty(hudson.security.AuthorizationMatrixProperty)
  
  //println(proj);
}
