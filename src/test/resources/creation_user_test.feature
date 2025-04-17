Feature: Create Licence

  Scenario: Successfully create a new licence
  Given the licence to create is for "John" "Doe"
  Given the driver lives in "Rome"
  Given and he was born in "15-08-1990"
  Then the creation of the licence should be correct