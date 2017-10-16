import sys
from PyQt5 import QtWidgets

class Window(QtWidgets.QWidget):
    def __init__(self):
        super().__init__()
        self.initUi()

    def initUi(self):
        self.b1 = QtWidgets.QPushButton('push1')
        self.b2 = QtWidgets.QPushButton('push2')
        self.t1 = QtWidgets.QLineEdit()
        self.t2 = QtWidgets.QLineEdit()
        self.l1 = QtWidgets.QLabel('label 1')
        self.l2 = QtWidgets.QLabel('label 2')

        hbox = QtWidgets.QHBoxLayout()
        hbox.addWidget(self.b1)
        hbox.addWidget(self.b2)

        vbox = QtWidgets.QVBoxLayout()
        vbox.addWidget(self.l1)
        vbox.addWidget(self.t1)
        vbox.addWidget(self.l2)
        vbox.addWidget(self.t2)
        vbox.addLayout(hbox)

        self.setLayout(vbox)

        self.setWindowTitle('learn yo')
        self.show()


app = QtWidgets.QApplication(sys.argv)
w = Window()
sys.exit(app.exec_())
