pipeline{
    agent any

    stages{
        stage{
            steps {
                git 'https://github.com/Mahadeo51/ELKConnection.git'
                //sh './mvnw clean compile'
                 bat '.\mvnw clean compile'
            }
        }
    }
}