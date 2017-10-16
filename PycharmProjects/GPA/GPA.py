import sys
from PyQt5.QtWidgets import QApplication, QMainWindow
from uicode import Ui_MainWindow

class Ui_MainWindow(QMainWindow, Ui_MainWindow):
    # fields
    classes = {}  # {classname: (credits, grade)}
    gpa = 0.0
    credits = 0

    def __init__(self):
        super(Ui_MainWindow, self).__init__()

        self.setupUi(self)


        #do stuff
        self.addClass.clicked.connect(self.add)
        self.deleteClass.clicked.connect(self.delete)
        self.textArea.setText("Name\tCredits\tGrade\tGPA")

    def add(self):
        #print(self.classes[self.classText.toPlainText()][0])
        self.classes[self.classText.toPlainText()] = (self.creditsText.toPlainText(), self.gradeText.toPlainText())

        #self.textArea.append("\n%s\t%s\t%s\t%s", self.classText.toPlainText(), self.classes[self.classText.toPlainText()][0], self.classes[self.classText.toPlainText()][1], self.gpa)

    def delete(self):

        del self.classes[self.classText.toPlainText()]


def main():
    app = QApplication(sys.argv)
    window = Ui_MainWindow()
    window.show()
    sys.exit(app.exec_())

if __name__ == '__main__':
    main()
