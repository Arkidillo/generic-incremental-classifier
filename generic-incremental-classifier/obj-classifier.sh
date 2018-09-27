python ./src/svm_classifier/iterative-image-manager.py 0
java -jar ./generic-incremental-classifier.jar
python ./src/svm_classifier/train-classifier.py
python ./src/svm_classifier/iterative-image-manager.py 1
python ./src/svm_classifier/test-classifier.py
java -jar ./generic-incremental-classifier.jar 1
python ./src/svm_classifier/train-classifier.py
python ./src/svm_classifier/iterative-image-manager.py 2
python ./src/svm_classifier/test-classifier.py