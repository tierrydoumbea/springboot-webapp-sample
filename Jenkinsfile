pipeline {
    agent any

    tools {
        gradle "gradle"
    }
    environment{
        VERSION = "${env.BUILD_ID}"
        IMAGE_NAME = "devopstrainingschool/java-gradle"
    }
    stages {
        stage('clean') {
            
            steps {
             
            
                
                sh "gradle  clean"

            }

            
            
        }
        stage('Build') {
            steps {
              
                
               
                sh "gradle  build"

            }

             
        }
         stage("sonar quality check"){
          
            steps{
                script{
                    withSonarQubeEnv('sonarqube') {
                           
                            sh 'gradle sonarqube'
                    }

                   

                }  
            }
        
       
}

       stage('docker image') {
      
      steps {
       
       withDockerRegistry([ credentialsId: "Docker_hub", url: "https://index.docker.io/v1/" ]) {
       sh 'docker build . -t $IMAGE_NAME:$VERSION -f Dockerfile'
       sh 'docker push $IMAGE_NAME:$VERSION'
        
        }
       
        
     
         
      }
     }
    }
}
