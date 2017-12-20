import sys
import os
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget, QLabel, QLineEdit, \
     QVBoxLayout, QFileDialog, QHBoxLayout, QMainWindow, QAction

class Window(QMainWindow):
    def __init__(self):
        super(Window, self).__init__()

        self.wi = wig()
        self.setCentralWidget(self.wi)
        self.setupUi()

    def setupUi(self):
        bar = self.menuBar()
        filemenu = bar.addMenu('File')
        newact = QAction('New', self)
        openact = QAction('Open', self)
        saveact = QAction('Save', self)
        saveasact = QAction('Save as', self)
        filemenu.addAction(newact)
        filemenu.addAction(openact)
        filemenu.addAction(saveact)
        filemenu.addAction(saveasact)
        
        newact.triggered.connect(self.newT)
        openact.triggered.connect(self.openT)
        saveact.triggered.connect(self.saveT)
        saveasact.triggered.connect(self.saveAsT)

        self.wi.textArea.setDisabled(True)
        self.wi.textline.setDisabled(True)
        self.setWindowTitle('Project2')
        self.show()
    def newT(self):
        self.wi.textArea.setDisabled(False)
        self.wi.label.setText('*')

    def openT(self):
        filename = QFileDialog.getOpenFileName(self, 'Select File to Open', os.getenv('HOME'))
        try:
            with open(filename[0], 'r') as f:
                txt = f.read()
                self.wi.textArea.setText(txt)

        except Exception as e:
            print(e)

        self.wi.textArea.setDisabled(False)

    def saveT(self):
        try:
            with open(self.wi.textline.text()) as f:
                txt = self.wi.textArea.toPlainText()
                f.write(txt)
        except Exception as e:
            print(e)

    def saveAsT(self):
        filename = QFileDialog.getSaveFileName(self, 'Select Filename to Save as', os.getenv('HOME'))
        try:
            with open(filename[0], 'w') as f:
                txt = self.wi.textArea.toPlainText()
                f.write(txt)
        except Exception as e:
            print(e)


class wig(QWidget):
    def __init__(self):
        super(wig, self).__init__()
        self.setupUi()

    def setupUi(self):
        self.label = QLabel(' ')
        self.textArea = QTextEdit()
        self.textline = QLineEdit()

        mainLayout = QVBoxLayout()
        toplayout = QHBoxLayout()
        toplayout.addWidget(self.label)
        toplayout.addWidget(self.textline)
        mainLayout.addLayout(toplayout)
        mainLayout.addWidget(self.textArea)
        self.setLayout(mainLayout)

        self.show()


app = QApplication(sys.argv)
w = Window()
sys.exit(app.exec_())
