pipeline{
 agent any
 triggers {
   pollSCM '* * * * *'
 }
 stages {
   stage('Build'){
      steps {
        script {
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