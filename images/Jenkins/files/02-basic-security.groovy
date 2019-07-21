#!groovy

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)

def admin_name = System.getenv()['admin_name']
def admin_password = System.getenv()['admin_password']

hudsonRealm.createAccount(admin_name,admin_password)
instance.setSecurityRealm(hudsonRealm)

def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, admin_name)
instance.setAuthorizationStrategy(strategy)

instance.save()
