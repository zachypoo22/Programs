import tkinter as tk
from tkinter import ttk
win = tk.Tk()
win.title('I dunno')

# l = ttk.Label(win, text='WOAH A LBELEE THO')
# l.grid(column=0, row=0)

def clickMe():
    action.configure(text='hello ' + name.get())

ttk.Label(win, text='enter a name').grid(column=0, row=0)

name = tk.StringVar()
nameEntered = ttk.Entry(win, width=12, textvariable=name)
nameEntered.grid(column=0, row=1)

action = ttk.Button(win, text='click this', command=clickMe)
action.grid(column=3, row=1)

ttk.Label(win, text='chose a number').grid(column=1, row=0)
number = tk.StringVar()
numberChosen = ttk.Combobox(win, width=12, textvariable=number)
numberChosen['values'] = (1, 2, 4, 42, 100)
numberChosen.grid(column=1, row=1)
numberChosen.current(0)

nameEntered.focus()

win.mainloop()