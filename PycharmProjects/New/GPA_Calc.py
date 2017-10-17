import sys
import sqlite3
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QLineEdit,\
    QTextEdit, QPushButton, QLabel, QHBoxLayout, QInputDialog


#setup DataBase
conn = sqlite3.connect('classes.db')
c = conn.cursor()
c.execute('''CREATE TABLE IF NOT EXISTS  classes (cname text, credits integer, grade real  )''')


class Window(QWidget):

    def __init__(self):
        super().__init__()
        self.setupUi()
        self.main()

    def setupUi(self):

        #classname objects
        classLayout = QHBoxLayout()
        classLabel = QLabel('Class\t')
        self.classText = QLineEdit()
        classLayout.addWidget(classLabel)
        classLayout.addWidget(self.classText)

        #credits objects
        creditsLayout = QHBoxLayout()
        creditsLabel = QLabel('Credits\t')
        self.creditsText = QLineEdit()
        creditsLayout.addWidget(creditsLabel)
        creditsLayout.addWidget(self.creditsText)

        #grade objects
        gradeLayout = QHBoxLayout()
        gradeLabel = QLabel('Grade\t')
        self.gradeText = QLineEdit()
        gradeLayout.addWidget(gradeLabel)
        gradeLayout.addWidget(self.gradeText)

        #button objects
        buttonLayout = QHBoxLayout()
        self.addButton = QPushButton('Add')
        self.deleteButton = QPushButton('Delete')
        buttonLayout.addWidget(self.addButton)
        buttonLayout.addWidget(self.deleteButton)

        #entire window
        self.textArea = QTextEdit()
        winLayout = QVBoxLayout()
        winLayout.addLayout(classLayout)
        winLayout.addLayout(creditsLayout)
        winLayout.addLayout(gradeLayout)
        winLayout.addLayout(buttonLayout)
        winLayout.addWidget(self.textArea)

        self.setLayout(winLayout)
        self.show()

    def main(self):
        self.addButton.clicked.connect(self.add)
        self.deleteButton.clicked.connect(self.delete)

        #init text area
        self.calcGPA()
        self.updateText()

        #test code
        # self.insertDB(('CS3', 3, 99.0))


    def add(self):
        cTup = (self.classText.text(), int(self.creditsText.text()), float(self.gradeText.text()))
        self.insertDB(cTup)

        #calc new GPA and update text area
        self.calcGPA()
        self.updateText()

    def delete(self):
        # conn = sqlite3.connect('classes.db')
        # c = conn.cursor()
        dname, entered = QInputDialog.getText(self, "Delete Class", "Class Name:")

        if not entered:
            return
        else:
            self.delDB(dname)

        #calc new GPA and update text area

        self.calcGPA
        self.updateText()

    def calcGPA(self):
        self.gpa = 0

    def updateText(self):
        conn = sqlite3.connect('classes.db')
        c = conn.cursor()
        self.textArea.setText('GPA : {} \nclass \tgrade\n========\t========'.format(self.gpa))

        c.execute('''SELECT * FROM classes''')
        clist = c.fetchall()

        for item in clist:
            self.textArea.append("{}\t{}\n--------\t--------".format(item[0], item[2]))


    def insertDB(self, tup):
        conn = sqlite3.connect('classes.db')
        c = conn.cursor()
        print(tup)
        print('executing')
        print('''INSERT INTO classes VALUES ('{}', {}, {})'''.format(tup[0], tup[1], tup[2]))
        c.execute('''INSERT INTO classes VALUES ('{}', {}, {})'''.format(tup[0], tup[1], tup[2]))
        print('commiting')
        conn.commit()

    def delDB(self, classname):
        conn = sqlite3.connect('classes.db')
        c = conn.cursor()
        print(classname)
        c.execute('''DELETE FROM classes WHERE cname = '{}' '''.format(classname))
        conn.commit()



app = QApplication(sys.argv)
window = Window()
# close connection
conn.close()
sys.exit(app.exec_())