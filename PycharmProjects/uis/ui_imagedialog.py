# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'theui.ui'
#
# Created by: PyQt5 UI code generator 5.9
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets


class ImageDialog(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(361, 390)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.changeButton = QtWidgets.QPushButton(self.centralwidget)
        self.changeButton.setGeometry(QtCore.QRect(10, 140, 101, 41))
        self.changeButton.setObjectName("changeButton")
        self.dumpButton = QtWidgets.QPushButton(self.centralwidget)
        self.dumpButton.setGeometry(QtCore.QRect(180, 140, 101, 41))
        self.dumpButton.setObjectName("dumpButton")
        self.userNameText = QtWidgets.QTextEdit(self.centralwidget)
        self.userNameText.setGeometry(QtCore.QRect(120, 20, 171, 31))
        self.userNameText.setObjectName("userNameText")
        self.oldPasswordText = QtWidgets.QTextEdit(self.centralwidget)
        self.oldPasswordText.setGeometry(QtCore.QRect(120, 60, 171, 31))
        self.oldPasswordText.setObjectName("oldPasswordText")
        self.newPasswordText = QtWidgets.QTextEdit(self.centralwidget)
        self.newPasswordText.setGeometry(QtCore.QRect(120, 100, 171, 31))
        self.newPasswordText.setObjectName("newPasswordText")
        self.textArea = QtWidgets.QTextBrowser(self.centralwidget)
        self.textArea.setGeometry(QtCore.QRect(10, 190, 281, 151))
        self.textArea.setObjectName("textArea")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(60, 20, 71, 31))
        font = QtGui.QFont()
        font.setPointSize(10)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.label_2 = QtWidgets.QLabel(self.centralwidget)
        self.label_2.setGeometry(QtCore.QRect(20, 70, 81, 20))
        font = QtGui.QFont()
        font.setPointSize(10)
        self.label_2.setFont(font)
        self.label_2.setObjectName("label_2")
        self.label_3 = QtWidgets.QLabel(self.centralwidget)
        self.label_3.setGeometry(QtCore.QRect(20, 110, 91, 16))
        font = QtGui.QFont()
        font.setPointSize(10)
        self.label_3.setFont(font)
        self.label_3.setObjectName("label_3")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 361, 21))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "Password Changer"))
        self.changeButton.setText(_translate("MainWindow", "Change Password"))
        self.dumpButton.setText(_translate("MainWindow", "Dump Data"))
        self.label.setText(_translate("MainWindow", "Login"))
        self.label_2.setText(_translate("MainWindow", "Old Password"))
        self.label_3.setText(_translate("MainWindow", "New Password"))

