from me.repository import *

from sklearn.preprocessing import StandardScaler


def normalisation(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        # encode each sample into a list
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]

        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data

        # decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData


angry, fear, happy, sad = load_data()

train_data = []
train_output = []
test_data = []
test_output = []

for i in angry[:int(len(angry) * 0.8)]:  # 80% for training
    train_data.append(i)
    train_output.append('angry')

for i in fear[:int(len(fear) * 0.8)]:  # 80% for training
    train_data.append(i)
    train_output.append('fear')

for i in happy[:int(len(happy) * 0.8)]:  # 80% for training
    train_data.append(i)
    train_output.append('happy')

for i in sad[:int(len(sad) * 0.8)]:  # 80% for training
    train_data.append(i)
    train_output.append('sad')

for i in angry[int(len(angry) * 0.8):]:  # 20% for test
    test_data.append(i)
    test_output.append('angry')

for i in fear[int(len(fear) * 0.8):]:
    test_data.append(i)
    test_output.append('fear')

for i in happy[int(len(happy) * 0.8):]:
    test_data.append(i)
    test_output.append('happy')

for i in sad[int(len(sad) * 0.8):]:
    test_data.append(i)
    test_output.append('sad')

train_data_n, test_data_n = normalisation(train_data, test_data)
from sklearn import svm

clf = svm.SVC(kernel='linear')  # Linear Kernel
clf.fit(train_data_n, train_output)
y_pred = clf.predict(test_data_n)

from sklearn import metrics

print("Accuracy:", metrics.accuracy_score(test_output, y_pred))

from sklearn.metrics import classification_report, confusion_matrix

print(classification_report(test_output, y_pred))
