// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

def appName = grails.util.Metadata.current.'app.name'
def baseURL = grails.serverURL ?: "http://127.0.0.1:8080/${appName}"

oauth {
  // ...
  providers {
    // for Google OAuth 2.0
    google {
      api = org.grails.plugin.springsecurity.oauth.GoogleApi20
      key = '1056211852217-g9bte622em66q3sob1lili9b5ussp46a.apps.googleusercontent.com'
      secret = 'gD0Gn2H7Fuppc__d8ycgwx2V'
      successUri = '/oauth/google/success'
      failureUri = '/oauth/google/error'
      callback = "${baseURL}/oauth/google/callback"
      scope = 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
    }
    // ...
  }
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'googlesignin.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'googlesignin.UserRole'
grails.plugin.springsecurity.authority.className = 'googlesignin.Role'
grails.plugin.springsecurity.interceptUrlMap = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll'],
    '/login/**':                      ['permitAll'],
    '/logout/**':                     ['permitAll'],
    '/oauth/**':                      ['permitAll']
]


// Added by the Spring Security OAuth plugin:
grails.plugin.springsecurity.oauth.domainClass = 'googlesignin.OAuthID'


// Instead of using InterceptUrlMap you can choose to use annotations on your controllers.
// Do the following:
//      * Add @Secured annotations to your controllers
//      * Remove the entire "grails.plugin.springsecurity.interceptUrlMap" above
//      * Change the "grails.plugin.springsecurity.securityConfigType" below
//      * Uncomment the lines below regarding securityConfigType and staticRules
// See this blog for more details: http://theexceptioncatcher.com/blog/2015/04/spring-security-oauth-the-missing-instructions/
grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
//grails.plugin.springsecurity.securityConfigType = 'Annotation'
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//        '/':                              ['permitAll'],
//        '/index':                         ['permitAll'],
//        '/index.gsp':                     ['permitAll'],
//        '/login/**':                      ['permitAll'],
//        '/assets/**':                     ['permitAll'],
//        '/**/js/**':                      ['permitAll'],
//        '/**/css/**':                     ['permitAll'],
//        '/**/images/**':                  ['permitAll'],
//        '/**/favicon.ico':                ['permitAll'],
//        '/oauth/**':                      ['permitAll'],
//        '/springSecurityOAuth/**':        ['permitAll']
//]

// Allow logout with a GET request
grails.plugin.springsecurity.logout.postOnly = false
