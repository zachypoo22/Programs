# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'theui.ui'
#
# Created by: PyQt5 UI code generator 5.9
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(290, 518)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(110, 0, 51, 31))
        font = QtGui.QFont()
        font.setPointSize(11)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.classText = QtWidgets.QTextEdit(self.centralwidget)
        self.classText.setGeometry(QtCore.QRect(10, 30, 261, 31))
        self.classText.setObjectName("classText")
        self.label_2 = QtWidgets.QLabel(self.centralwidget)
        self.label_2.setGeometry(QtCore.QRect(110, 60, 51, 31))
        font = QtGui.QFont()
        font.setPointSize(11)
        self.label_2.setFont(font)
        self.label_2.setObjectName("label_2")
        self.creditsText = QtWidgets.QTextEdit(self.centralwidget)
        self.creditsText.setGeometry(QtCore.QRect(10, 100, 261, 31))
        self.creditsText.setObjectName("creditsText")
        self.label_3 = QtWidgets.QLabel(self.centralwidget)
        self.label_3.setGeometry(QtCore.QRect(100, 130, 71, 31))
        font = QtGui.QFont()
        font.setPointSize(11)
        self.label_3.setFont(font)
        self.label_3.setObjectName("label_3")
        self.gradeText = QtWidgets.QTextEdit(self.centralwidget)
        self.gradeText.setGeometry(QtCore.QRect(10, 170, 261, 31))
        self.gradeText.setObjectName("gradeText")
        self.textArea = QtWidgets.QTextBrowser(self.centralwidget)
        self.textArea.setGeometry(QtCore.QRect(10, 270, 261, 192))
        self.textArea.setObjectName("textArea")
        self.addClass = QtWidgets.QPushButton(self.centralwidget)
        self.addClass.setGeometry(QtCore.QRect(10, 220, 101, 31))
        self.addClass.setObjectName("addClass")
        self.deleteClass = QtWidgets.QPushButton(self.centralwidget)
        self.deleteClass.setGeometry(QtCore.QRect(170, 220, 101, 31))
        self.deleteClass.setObjectName("deleteClass")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 290, 21))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "GPA Calculator"))
        self.label.setText(_translate("MainWindow", "Class"))
        self.label_2.setText(_translate("MainWindow", "Credits"))
        self.label_3.setText(_translate("MainWindow", "Grade %"))
        self.addClass.setText(_translate("MainWindow", "Add Class"))
        self.deleteClass.setText(_translate("MainWindow", "Delete"))

