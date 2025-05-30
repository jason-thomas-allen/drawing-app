# Drawing App

Requires
* Java 17
* Maven 3.9

## Build
```sh
$ mvn clean package
```

## Run
```sh
java -jar ./target/drawing-1.0-SNAPSHOT.jar
```

## Run All Tests
```sh
mvn clean test
```

## Test Coverage
JaCoCo report written to ./target/site/jacoco/index.html

## Notes

Canvas origin ( 0,0 ) is top-left.
i.e. Point (3, 2) is 3 pixels to the right of the origin on the x-axis, and 2 points below origin on the y-axis.

Lines and rectangles can be drawn in either direction.
e.g. L 3 1 2 2 x produces the same result as L 2 2 3 2 x
     R 1 1 3 3 . produces the same results as R 3 3 1 1 .

Coordinates are 1-based and cannot be negative.

Canvas dimensions cannot be less than 2.

Lines can only be drawn vertically or horizontally. i.e. no support for diagonals.

Lines or rectangles extending beyond the boundary of the canvas will be clipped.

Commands can be specified in upper or lower case.

