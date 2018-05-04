java -jar ./generic-incremental-classifier.jar
python ./src/svm_classifier/extract-features.py
python ./src/svm_classifier/train-classifier.py
python ./src/svm_classifier/test-classifier.py
java -jar ./generic-incremental-classifier.jar 1
python ./src/svm_classifier/extract-features.py
python ./src/svm_classifier/train-classifier.py
python ./src/svm_classifier/test-classifier.py