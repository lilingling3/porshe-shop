#!/usr/bin/env groovy

node {
    def MAIN_VERSION = 1
    def SMALL_VERSION = 1
    def group = 'harbor.boldseas.com'
    def nameSpace = "porsche"
    def mailList = 'ants@boldseas.com'
    def branch = BRANCH_NAME
    String version = "${branch}.${MAIN_VERSION}.${SMALL_VERSION}.${BUILD_NUMBER}"
    String testLatestVersion = "${branch}.latest"
    boolean needPublish
    def isPublishOk = false
    def images = []

    stage('Preparation') {
        checkout scm
        needPublish = (branch == "master")
        try {
            sh 'docker images | grep "<none>" | awk \'{print $3}\' | xargs docker rmi'
            sh 'docker rmi $(docker images -q -f "dangling=true")'
        }
        catch (Exception ignored) {
        }
    }

    stage('Build') {
        try {
            sh "./gradlew clean build"
        }
        catch (Exception e) {
            mail from: 'jenkins', to: mailList, subject: "[构建失败]<PORSCHE-SHOP-${branch}>自动构建失败", body: "分支<${branch}>构建失败，请前往${BUILD_URL}确认失败详情，尽快修复并重新提交。"
            throw e
        }
        finally {
            junit '**/test-results/**/*.xml'
            jacoco()
        }
    }

    stage('Sonar') {
        sh './gradlew sonarqube -Dsonar.host.url=http://192.168.1.246:9000 -Dsonar.login=d030ff6de0ef3dd4a4d61c58395200e9a936d64f'
    }

    stage('BuildImages') {
        if (!needPublish) return

            sh "rm -rf build/docker/"
            sh "mkdir -p build/docker && cp -f ./Dockerfile  build/docker/ && cp -f build/libs/*[^sources].jar build/docker/"
            sh "cp ./release/font/* ./build/docker"
            dir("build/docker") {
                def appName = 'porsche-shop'
                echo("Build image ${appName}")
                def img = docker.build("${group}/${nameSpace}/${appName}:${version}")
                images.push(img)
        }
    }

    stage('PushImages') {
        if (!needPublish) return
        for (i = 0; i < images.size(); i++) {
            def image = images[i]
            image.tag("${testLatestVersion}")

            echo("Push image:${image.imageName()}")

            image.push()
            image.push("${testLatestVersion}")
        }
    }

    stage("Publish") {
        if (!needPublish) return

        echo 'Begin publish to preview environment...'

        try {
            dir("release/test") {
                try {
                    sh 'kubectl -s http://192.168.2.212:8080 delete -f ./ --now'
                } catch (Exception ignored) {
                }

                sh 'kubectl -s http://192.168.2.212:8080 create -f ./'
            }
            isPublishOk = true
        }
        finally {
            def msgBody
            if (isPublishOk) {
                msgBody = "porsche-shop发布test环境成功，版本号:${version}，请尽快进行测试"
            }

            String subject = "[${isPublishOk ? "成功" : "失败"}]<porsche-shop-${branch}>发布到test完成";
            mail from: 'jenkins',
                    body: "${msgBody}",
                    subject: subject,
                    to: mailList

        }
    }
}