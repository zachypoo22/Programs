import sys
from PyQt5.QtWidgets import QLineEdit, QSlider, QPushButton, QVBoxLayout, QApplication, QWidget
from PyQt5.QtCore import Qt

class Window(QWidget):

    def __init__(self):
        super().__init__()

        self.initui()

    def initui(self):
        self.le =QLineEdit()
        self.b = QPushButton('clear')
        self.b2 = QPushButton('print')

        self.s1 = QSlider(Qt.Horizontal)
        self.s1.setMinimum(1)
        self.s1.setMaximum(99)
        self.s1.setValue(25)
        self.s1.setTickInterval(10)
        self.s1.setTickPosition(QSlider.TicksBelow)


        vbox = QVBoxLayout()
        vbox.addWidget(self.le)
        vbox.addWidget(self.b)
        vbox.addWidget(self.b2)
        vbox.addWidget(self.s1)

        self.setLayout(vbox)

        self.b.clicked.connect(lambda: self.btn(self.b, "Hello from clear"))
        self.b2.clicked.connect(lambda: self.btn(self.b, "hello from print"))
        self.s1.valueChanged.connect(self.vchange)

        self.setWindowTitle('learn this')
        self.show()

    def btn(self, b, string):
        if b.text() == "print":
            print(self.le.text())
        else:
            self.le.clear()
        print(string)

    def vchange(self):
        myvalue = str(self.s1.value())
        self.le.setText(myvalue)


app = QApplication(sys.argv)
w = Window()
sys.exit(app.exec_())
