pipeline{
 agent any
 triggers {
   pollSCM '* * * * *'
 }
 stages {
   stage('Build'){
      steps {
        script {
                           def mvnHome = tool 'Maven 3.5.2'
                           if (isUnix()) {

                               sh "'${mvnHome}/bin/mvn'  clean install -Dunit-tests.skip=true"
                           } else {
                               bat(/"${mvnHome}\bin\mvn" clean install -Dunit-tests.skip=true/)
                           }

                       }

      }
   }
 }
}