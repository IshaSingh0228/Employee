pipeline{
  agent any
  tools{
  maven 'maven3'
  }
  stages{
  stage('Compile'){
  steps{
  sh 'mvn compile'
  } 
  }
  stage('Testing'){
  steps{
  sh 'mvn test'
  } 
  }
  stage('Packaging'){
  steps{
  sh 'mvn package'
  } 
  }
  }
   post{
    success{
        junit '**/target/surefire-reports/TEST-*.xml'
        archiveArtifacts 'target/*.jar'
    }
  }
  
}
