#!/usr/bin/env groovy

import hudson.model.User
import hudson.security.Permission

import hudson.model.*
  
import jenkins.model.*

import hudson.security.*

import com.cloudbees.plugins.*

import hudson.scm.*
  
import hudson.security.PermissionGroup;
import hudson.security.Permission;
import hudson.security.PermissionScope;

public class MyPlugin extends Plugin
{
    // create permission group
    private static final PermissionGroup pg =
      new PermissionGroup( MyPlugin.class,
                           Messages._MyPermissionGroupTitle() // This is the column title
                         );

    // setup permission in that group
    public static final Permission perm =
        new Permission( pg,
                        "MyPermission",
                        Messages._MyPermissionDescription(),
                        Jenkins.ADMINISTER
                      );
}



def call(String name = 'human') {
  /*
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
 
 */
  def user2 = currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
   println("Logged User : - "+user2);
  
  
 //def proj = Hudson.instance.getItem("script-job")
  //def proj = Jenkins.getInstance().getItemByFullName(env.JOB_BASE_NAME, Job.class)
  
  //AuthorizationMatrixProperty authProperty = proj.getProperty(hudson.security.AuthorizationMatrixProperty)
  
  
  def jen = Jenkins.getInstance();
  //def jobname = "script-job"
  def jobname = env.JOB_BASE_NAME
  println(" Job : " + jobname)
  def jobobj = jen.getItem(jobname);
  def jobprop = jobobj.getProperty(AuthorizationMatrixProperty.class)
  def currPermissionSet = jobprop.getGrantedPermissions();
  
   Map<hudson.security.Permission,Set<String>>  gp = jobprop.getGrantedPermissions();
   
   //println(gp);
  
   gp.each{ us ->
          println(us);
     println us.getClass();
  
   }
  //GlobalMatrixAuthorizationStrategy auth = new GlobalMatrixAuthorizationStrategy();
  
  //println(currPermissionSet );
}
