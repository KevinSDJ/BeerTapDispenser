# Notes

#### command to run test by profile , /mvnw spring-boot:run -Dspring-boot.run.profiles=prod
---
#### UUID config : this setup didn't cause me problems with jackson parse and projections 
---

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    @Column(name = "_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(46)")
    private UUID _id; 
>>