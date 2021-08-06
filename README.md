# MockSnifferTests

- This project is created to test the functionality of MockSniffer Project on different cases.
- Source code of MockSniffer can be found [here](https://github.com/henryhchchc/MockSniffer) alongside with the links to the original paper.
- Also, a summary of the MockSniffer project can be found [here](https://docs.google.com/document/d/1my1nfR7acrVHna_H-ZO6knYoweI6twsjOlN72oKugLU/edit?usp=sharing)

## Running MockSniffer
I have run the code through the instructions of the authors on different projects, and some results and challenges are mentioned here.
<br/> Before talking about the different projects and results, I want to point to some errors that you may run into while going through different steps of MockSniffer:

### Build Problems
Fortunately, there was no problem for me during the building of MockSniffer itself, but building other projects sometimes 
is not this easy, as an example while building [pdfbox](https://github.com/apache/pdfbox) some tests failed (but I think
it was built successfully). You are on your own for struggling with old projects build files!

### Obtaining ML models
I was a little confused with <i> [obtaining the ML model](https://github.com/apache/pdfbox) </i> part of the MockSniffer
documentation at first, but it seems that you can easily download mock-sniffer.pmml file from [here](https://github.com/henryhchchc/MockSniffer/releases/tag/v1.1.0),
but I'm still not sure whether actions for training the model should be taken or not.

### Step 1: Extract dependency pairs from the target project
