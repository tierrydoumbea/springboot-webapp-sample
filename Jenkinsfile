pipeline {
 agent any 
 tools { gradle 'javagradle' }
 stages {
    stage (' gradle clean ') {
      steps {
       sh 'gradle clean'
      }
    
    
    }
  stage ( 'Gradle Build' ) {
   steps {
    sh 'gradle build'
  }
  
  }
  stage ( 'Docker Build ' ) {
   steps {
    sh 'docker build -t thierrydoumbea/javagradle .'
  }
  
  }
  
    stage ( 'Docker Push' ) {
   steps {
    withDockerRegistry([ credentialsId: "Awa", url: "https://index.docker.io/v1/" ]) {
    sh " docker push thierrydoumbea/javagradle "
    }
  }
  
  }
  
  }






















}
