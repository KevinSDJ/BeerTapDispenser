# Notes

#### command to run test by profile , /mvnw spring-boot:run -Dspring-boot.run.profiles=prod

---

#### UUID config : this setup didn't cause me problems with jackson parse and projections

---

* steps

* [X] create dispenser
* [X] change status open
* [ ] by change status create usage
* [ ] by create usage and  add the dispenser referer
* [ ] after change status closed
* [ ] find the last open usage
* [ ] update usage amount in this usage and set data now and save
* [ ] next if usage is the first crate a summary and set data or update summary if exists
