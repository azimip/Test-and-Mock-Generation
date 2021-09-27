##### This project includes different experiments for test (and mock) generation, using _PANKTI_ and _MOCK SNIFFER_ projects and is separated to 3 parts:
- [mock sniffer experiments](mock-sniffer-experiments/README.md), including different runs of it on [pdfbox](https://github.com/apache/pdfbox) and [commons-io](https://github.com/carlspring/commons-io) projects
- [pankti experiments](pankti-experiments/README.md), including a few runs on some parts of [commons-io](https://github.com/carlspring/commons-io) project
- [pankti and mocksniffer](pankti-and-mocksniffer), which is an experiment to get the intersection of pankti output on _pdfbox_ project as compared to MockSniffer outputs on it

##Timeline
**I have started the project on July 2021 and gone this way so far:**
### July 21st
- I've read these papers partly:
  - Machine Learning Applied to Software Testing: A Systematic Mapping Study (2019) Vinicius H. S. Durelli, Rafael S. Durelli, Simone S. Borges, Andre T. Endo, Marcelo M. Eler, Diego Dias, and Marcelo P. Guimaraes
  - Using Machine Learning to Generate Test Oracles: A Systematic Literature Review (2021) Afonso Fontes, Gregory Gay
  - Production Monitoring to Improve Test Suites (2021) Deepika Tiwari, Long Zhang, Martin Monperrus, and Benoit Baudry
- Prepared [a presentation](Using ML to Generate Test Oracles.pptx) about the use of ML on generating tests
- Learned more about testing oracles and software testing by reading different articles

### August 6th
- Dug more on mocking and different practices of it, reading Medium articles and also getting familiar with Mockito, read [this paper](https://link.springer.com/article/10.1007/s10664-018-9663-0) about mocking
- Read, comprehended, and summarized the MockSniffer paper:
  - Link to [Highlighted paper](https://kami.app/2k1-sTH-Z2v)
  - Link to my [notes and summaries](https://docs.google.com/document/d/1my1nfR7acrVHna_H-ZO6knYoweI6twsjOlN72oKugLU/edit?usp=sharing)
- Started working on [MockSniffer codebase](https://github.com/henryhchchc/MockSniffer) and familiarizing with it, also running the first step.
- Created this project on git!

### August 11th
- Looked for some small, concise project to run on MockSniffer and comprehend the code better
- Ran MockSniffer completely on pdfbox, and fixed some problems while running (documented on README)
- Faced some confusing names and codes and columns, for example it didn't work on projects without test, or some _ord_ column was in every csv, not documented

### August 17th
- Found commons-io project, a perfect choice for my tests since it was very brief and understandable
- Ran MockSniffer on commons-io and comprehend results in a much better way
- Understood _ORD_ field, changed some parts of the code to understand the functionality better
- removed the condition limiting the project to run only on projects with tests, and added another simple input to it

### August 24th
- Started working on PANKTI
- Comprehend _PIT_ and _DESCARTES_ libraries and ran them on some projects
- Worked on first step of PANKTI and suggested some minor changes on documentation and a simple fix on the code
- Faced different problems while running _DESCARTES_ on commons-io, not successful to fix them yet
- Read some parts of PANKTI code precisely

### August 30th
- **Ran PANKTI on commons-io!**
- Fixed the problems of commons-io with PIT and DESCARTES (they were mostly because one of the tests and using different version of junit)
- Created some REAL! test cases for a part of commons-io which was pseudo-tested according to PIT
- Tried to comprehend generated codes with more details
- Problem: generated tests didn't work when they were moved to commons-io codebase :(

### September 8th
- Fixed the problem of commons-io with generated test! 
- Documented PANKTI experiment and different problems I faced [here](pankti-experiments/README.md)
- Started working with MockSniffer and Pankti together, used the previous results of Pankti on PDFBox instead of commons-io, since there was no mock recommendation for commons-io by MockSniffer
- Worked with previous results of Pankti, tried to comprehend them in the best possible way
- Started working with Mockito in order to generate real mocks

### September 13th
- Compared the results of MockSniffer and Pankti on PDFBox:
  - Written a [python script](pankti-and-mocksniffer/pdfbox/get-intersection.py) in order to find out about recommendations of MockSniffer on generated tests of Pankti
  - Examined the results carefully, and there was no suitable intersection; the dependencies suggested by MockSniffer were not used on Pankti generated tests
- Worked with Mockito. Faced quite a few problems with IntelliJ and pom.xml file while trying to create a simple JAVA project to work with Mockito, and fixed them thanks to Deepika
- Read different samples of Mockito and examined some of them
- Read Deepika suggestions on Mockito proper inputs ([here](https://github.com/Deee92/journal/blob/master/notes/mocking-prelim.md) and [here](https://github.com/Deee92/journal/blob/master/notes/mocking-basics.md))
