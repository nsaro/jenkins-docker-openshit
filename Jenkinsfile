pipeline {
  agent any
  tools {
    maven "M3"
    jdk "openjdk-11"
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
    stage('artifact id') {
      steps {
        script {
          String formattedDate = new Date().format('yyyy-MM-dd_HH_mm')
          env.artifact_id = formattedDate + "_" + env.GIT_COMMIT[0. .6]
          currentBuild.description = "${env.artifact_id}"
          echo "Artifact Identifier: ${env.artifact_id}"
        }
      }
    }
    stage('Build Jar') {
      steps {
        echo '------------------Building Jar file------------------'
        git 'https://github.com/nsaro/jenkins-docker-openshit.git'
        sh "mvn -Dmaven.test.failure.ignore=true clean package"
      }
    }
    stage('Building image') {
      steps {
        script {
          echo '------------------Building Image------------------'
          dockerImage = docker.build registry + ":${env.artifact_id}"
        }
      }
    }
    stage('Deploy image') {
      steps {
        script {
          echo '------------------Deploying Image------------------'
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Cleaning up') {
      steps {
        echo '------------------Clearing Resources------------------'
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
    stage('Test Openshift') {
          steps {
            echo '------------------Test Openshift------------------'
            sh "oc get namespace"
            echo "Artifact Identifier: ${env.artifact_id}"
          }
        }
  }
}
