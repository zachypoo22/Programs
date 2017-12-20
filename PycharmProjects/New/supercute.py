import sys
from PyQt5.QtWidgets import QApplication, QWidget
from PyQt5.QtGui import QPainter, Qcolor, QFont
from PyQt5.QtCore import Qt

class Window(QWidget):

    def __init__(self):
        super().__init__()
        self.setupUi()
        self.main()

    def setupUi(self):

        disrect = QGraphicsRectItem
        lay = QVBoxLayout

        QStylePainter.render

        disrect.setRect(someRect)

        window.setLayout(lay)




app = QApplication(sys.argv)
window = Window()
sys.exit(app.exec_())