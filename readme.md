**Spring Batch Mastery**

```
(a)A Spring Batch Job,takes in a step

 The Job is the skeleton & is the most important thing,it is the event that
  is running.
   
 We need the JobRepository & Platform Transaction Manager to monitor the state of
  our Jobs.
```

**A Step**
```
 Inside the Job we have A Steps--> We will have a reader, writer & processor

A step can be chunked.
The Reader reads some data
The processor processes some data
The Writer received data from processor and will preform some write Operations
```

**Item Reader Configs**
```
resource(new ClassPathResource("book_data.csv"))

```
**Item Processor configs**

```
We can have multiple processors after the reader in a step.

```

**Advantages of Spring Batch**

```
(A)It is must faster and can easily group my records into chunks and update them
all at once.

(B)It also enables processing in your steps which is very difficult minus spring Batch

Imagine reading from  a CSV. Storing the results in an array or List, then processing
them then committing them to DB, Its is so manual,Spring Batch has a predefined way
on everything you may need to do when working with Large Datasets.
 
(C)Enables switching between readers, while maintaining the processing & write Steps.
````


**Exercise Two**
**(Reading from a Rest Endpoint as Input in A Spring Batch Application)**

```
A spring Batch Application can also read from a Rest Enpoint
```