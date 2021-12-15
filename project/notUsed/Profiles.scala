package notUsed

object Profiles {
  lazy val default: Map[String, String] = local

  lazy val local: Map[String, String] = base ++ Map(
    "port" -> "8081",
    "access.properties.path" -> "local/access.conf",
    "log.strategy" -> "STDOUT",
    "log.level" -> "DEBUG",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "30",
    "dataimport.log.save.days" -> "30",
    "log.path" -> "target/logs",
    "hl7.obfuscate.enabled" -> "false",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "true",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "true",
    "antiClickJackingEnabled" -> "false",
    "csrf.protection.disabled" -> "true",
    "dataimport.processing.targetEnv.config" -> "proc/local-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/local-two-node-cluster.conf"
  )

  lazy val stage: Map[String, String] = base ++ Map(
    "port" -> "8082",
    "access.properties.path" -> "stage/access.conf",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "120",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "$CATALINA_HOME/logs",
    "hl7.obfuscate.enabled" -> "false",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "false",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "true",
    "antiClickJackingEnabled" -> "true",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/stage-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/stage-cluster.conf",
    "tomcat.context.path.api" -> "/stage/api"
  )

  lazy val prod: Map[String, String] = base ++ Map(
    "port" -> "8083",
    "access.properties.path" -> "prod/access.conf",
    "es.number_of_shards" -> "6",
    "audit.log.save.days" -> "60",
    "warehouse.log.save.days" -> "365",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "false",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "false",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "false",
    "antiClickJackingEnabled" -> "true",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/prod-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/prod-cluster.conf"
  )
  /*
  lazy val `azure-qa`: Map[String, String] = base ++ Map(
    "access.properties.path" -> "azure-qa/access.conf",
    "es.number_of_shards" -> "6",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "120",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "false",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "false",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "true",
    "antiClickJackingEnabled" -> "true",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/azure-qa-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/azure-qa-cluster.conf"
  )

  lazy val `armor-dev`: Map[String, String] = base ++ Map(
    "access.properties.path" -> "dev/access.conf",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "120",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "true",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "true",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "false",
    "antiClickJackingEnabled" -> "false",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/dev-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/dev-cluster.conf"
  )

  lazy val `bioreference-dev`: Map[String, String] = base ++ Map(
    "access.properties.path" -> "bioreferenceDev/access.conf",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "120",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "true",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "true",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "true",
    "antiClickJackingEnabled" -> "false",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/bioreference-dev-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/bioreference-dev-cluster.conf"
  )

  lazy val `armor-qa`: Map[String, String] = base ++ Map(
    "access.properties.path" -> "qa/access.conf",
    "es.number_of_shards" -> "4",
    "audit.log.save.days" -> "30",
    "warehouse.log.save.days" -> "120",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "false",
    "hl7.obfuscate.accountId" -> "false",
    "service.error.show.messages" -> "false",
    "auth.biopeople.enable_external_users" -> "true",
    "external.accounts.mock.enabled" -> "true",
    "antiClickJackingEnabled" -> "true",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/qa-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/qa-cluster.conf"
  )

  lazy val `armor-demo`: Map[String, String] = base ++ Map(
    "access.properties.path" -> "demo/access.conf",
    "es.number_of_shards" -> "3",
    "audit.log.save.days" -> "60",
    "warehouse.log.save.days" -> "365",
    "dataimport.log.save.days" -> "365",
    "log.path" -> "${catalina.home}/logs",
    "hl7.obfuscate.enabled" -> "true",
    "hl7.obfuscate.accountId" -> "true",
    "service.error.show.messages" -> "false",
    "auth.biopeople.enable_external_users" -> "false",
    "external.accounts.mock.enabled" -> "false",
    "antiClickJackingEnabled" -> "true",
    "csrf.protection.disabled" -> "false",
    "dataimport.processing.targetEnv.config" -> "proc/demo-pool.conf",
    "dataimport.cluster.targetEnv.config" -> "env/demo-cluster.conf"
  )*/

  private lazy val base: Map[String, String] = Map(
    "es.number_of_shards" -> "2",
    "es.number_of_replicas" -> "0",
    "log.strategy" -> "LOGFILE",
    "log.level" -> "INFO",
    "cors.allowed.headers" -> "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,X-CSRF-Token",
    "cors.exposed.headers" -> "Access-Control-Allow-Origin,Access-Control-Allow-Credentials,X-CSRF-Token",
    "tomcat.context.path.api" -> "/api"
  )
}
