pipeline {
    agent any
    stages {
        stage ('Compile Stage') {
            steps {
                withMaven(maven : 'information-thematic-maven_3_8_1') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {
            steps {
                withMaven(maven : 'information-thematic-maven_3_8_1') {
                    sh 'mvn test'
                }
            }
        }

        stage ('Deployment Stage') {
            steps {
                withMaven(maven : 'information-thematic-maven_3_8_1') {
                    sh 'mvn deploy'
                }
            }
        }
    }
}