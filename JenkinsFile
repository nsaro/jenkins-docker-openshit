pipeline {
  agent any
  tools {
    maven "M3"
  }
  triggers {
    pollSCM 'H/1 * * * *'
  }
  environment {
    registry = "nsaro/spring-hello-world-v1"
    registryCredential = 'dockerhub_id'
    dockerImage = ''
  }
  stages {
    stage('Build Jar') {
      steps {
        git 'https://github.com/nsaro/jenkins-docker-openshit.git'
        sh "mvn -Dmaven.test.failure.ignore=true clean package"
      }
    }
    stage('Building image') {
      steps {
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy image') {
      steps {
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Cleaning up') {
      steps {
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}
