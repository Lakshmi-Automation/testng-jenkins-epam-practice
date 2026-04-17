<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="CalculatorSuite" parallel="methods" thread-count="4">
    <listeners>
        <listener class-name="com.epam.testNG.Task1.CustomListener"/>
    </listeners>
    <test name="CalculatorTests">
        <groups>
            <run>
                <include name="positive"/>
                <include name="negative"/>
            </run>
        </groups>
        <classes>
            <class name="com.epam.testNG.Task1.CalculatorTest"/>
        </classes>
    </test>
</suite>