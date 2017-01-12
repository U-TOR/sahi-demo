# Set up Instructions
1. Download Sahi Pro trial from <https://crm.sahipro.com/pay/index.php/registration/register>
2. Install Sahi Pro to your system
3. Add sahi.jar to the classpath
4. Start Sahi's proxy using
```
PATH_TO_SAHI/userdata/bin/start_sahi.bat 
```
or 
```
PATH_TO_SAHI/userdata/bin/start_sahi.sh
```
5. Run tests with
```
mvn clean install -P <BROWSER_NAME> -P <SUITE_NAME>
```
Add -P usetrail to enable integration with TestRail
6. Generate report with
```
mvn site
```
Generated report will be stored in ${basedir}\target\site\allure-maven-plugin\