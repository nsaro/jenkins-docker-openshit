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
    stage('artifact id') {
      steps {
        script {
          String formattedDate = new Date().format('yyyy-MM-dd_HH_mm')
          env.artifact_id = formattedDate + "_" + "$BUILD_NUMBER"
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
        sh "docker rmi $registry:${env.artifact_id}"
      }
    }
    stage('update openshift') {
        steps {
            echo '------------------Updating openshift configs------------------'
            sh "oc apply -f openShiftConfigs/service.yaml"
            sh "oc apply -f openShiftConfigs/route.yaml"
            sh "oc process -f openShiftConfigs/deployment-config.yaml -p DOCKER_TAG=${env.artifact_id} | oc apply -f -"
        }
    }
    stage('deploy') {
        steps {
            echo '------------------Deploying------------------'
            sh "oc rollout latest dc/spring-hello-world"
        }
    }
  }
}
