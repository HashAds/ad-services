# Hash Ads
The platform where advertisers pay ad viewers and ad hosting providers seamlessly via crypto currency.

## Running Hash Ads
This is a spring boot application and should be fairly easy to run. There is currently no DB used, so ad metadata is included in the application.properties file, and image urls must point to the actual png images hosted on another web server.

## Challenges

### Hedera SDK Multiple Bindings issue
To use the server, changes to the Hedera SDK POM needed to be made to exclude certain classes from the artifact. It is highly likely more should be excluded but in order to start a spring boot server using the sdk, the following changes were made to the shady "shaded" plugin section of the POM:

		      <plugin>
		        <groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-shade-plugin</artifactId>
		        <version>3.2.0</version>
		        <configuration>
              <artifactSet>
                <excludes>
                  <exclude>junit:junit</exclude>
                  <exclude>*:xml-apis</exclude>
                  <exclude>org.apache.maven:lib:tests</exclude>

                    <!--Exclusions added by the HashAds developers-->
                    <exclude>org.slf4j*</exclude>
                    <exclude>org.springframework*</exclude>
                    <exclude>com.fasterxml*</exclude>
                    
                </excludes>
              </artifactSet>
		        </configuration>
		        <executions>
		          <execution>
		            <phase>package</phase>
		            <goals>
		              <goal>shade</goal>
		            </goals>
		          </execution>
		        </executions>
		      </plugin>
		      
		      