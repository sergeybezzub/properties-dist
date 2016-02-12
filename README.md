Application supports folloing sources URI of properties:

1) Properties will be read by provided URL
- 'http://path/somefile.properties'
- 'file:///path/somefile.properties'
- 'http://path/somefile.json'
- 'file:///path/somefile.json'
2) Properties will be read from java project src/main/resources folder
- '/somefile.properties'
- '/somefile.json'

Current implementation supports following properties data types:

- String (default)
- Integer
- Double
- Boolean
- Amazone WS Regions
See the SupportedPropertiesType enum for more details.

The src/test/java includes unit tests for Manager, Property and Util classes.
Main.class could be used for test as well by following console command:

mvn clean package exec:java -Dexec.mainClass="com.crossover.trial.properties.Main" -Dexec.args="output1.txt /jdbc.properties /aws.properties /config.json"
"# properties-dist" 
