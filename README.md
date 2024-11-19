# Goal: Create an Investment application implemented using customized columnar database.

1. Clone the repository.
2. Compile Java files.
3. Run the application.

## Step 1: Clone the repository

Clone the repository custom-java-columnar-db from GitHub:

```bash
git clone https://github.com/hawardjie/custom-java-columnar-db.git
cd custom-java-columnar-db
```

## Step 2: Compile Java files

Compile the Java files your just cloned into a separate directory using the -d option with javac. This option specifies the destination diretory for the class files. If the directory does not exist, javac will create it.

```java
javac -d ../bin *.java
```

## Step 3: Run the application

After compilation, you can run your program using java command. You need to set the claspath to the directory containing the compiled class files using the -cp option. Then, specify the class name containing the main method. In this repository, the class name is Main.

```java
java -cp ../bin Main
```
