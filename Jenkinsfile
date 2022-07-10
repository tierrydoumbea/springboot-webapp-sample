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
             
            
                dir("spring-boot-app"){
                sh "gradle  clean"

            }

            
            }
        }
        stage('Build') {
            steps {
              
                
               dir("spring-boot-app"){
                sh "gradle  build"

            }

            } 
        }
         stage("sonar quality check"){
          
            steps{
                script{
                    withSonarQubeEnv('sonarqube') {
                           
                            sh 'mvn sonarqube'
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
