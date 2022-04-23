from cProfile import label
import numpy as np
import matplotlib.pyplot as plt

# Training Set

#datafile = '.\data\ex1data1.txt'
datafile = 'D:\TransportCompetition\Competition\结项论文\data_train(1).txt'


cols=np.loadtxt(datafile,delimiter=',',usecols=(0,1),unpack=True) #使用‘，’作为分隔符，读取第一列和第二列，UNpack=True将分列读取
#unpack：选择是否将数据向量输出，默认是False，即将数据逐行输出，当设置为True时，数据将逐列输出。
X=np.transpose(np.array(cols[:-1]))
y=np.transpose(np.array(cols[-1:]))
m=y.size
#X=np.insert(X,0,1,axis=1) #Insert the usual column of 1's into the "X" matrix
#arr原始数组，可一可多，obj插入元素位置，values是插入内容，axis是按行按列插入。
x_2=X**2 #每个元素都是X中的平方
x_3=X**3
x_4=X**4
x_5=X**5
x_6=X**6
x_7=X**7
x=np.ones(X.shape)
#构造好x数组m*(n+1)数组

x=np.append(x,(X-np.mean(X[:,0]))/(np.std(X[:,0])),axis=1)
x=np.append(x,(x_2-np.mean(x_2[:,0]))/(np.std(x_2[:,0])),axis=1)
x=np.append(x,(x_3-np.mean(x_3[:,0]))/(np.std(x_3[:,0])),axis=1)
x=np.append(x,(x_4-np.mean(x_4[:,0]))/(np.std(x_4[:,0])),axis=1)
x=np.append(x,(x_5-np.mean(x_5[:,0]))/(np.std(x_5[:,0])),axis=1)
x=np.append(x,(x_6-np.mean(x_6[:,0]))/(np.std(x_6[:,0])),axis=1)
x=np.append(x,(x_7-np.mean(x_7[:,0]))/(np.std(x_7[:,0])),axis=1)

#plot the data

plt.figure(figsize=(10,6)) #设置图像的宽和高
plt.plot(x[:,1],y[:,0],'rx',markersize=10)
plt.grid(True)  #显示网格线 可以用plt.grid(b=True,axis='x')表示只显示X轴网格线
plt.xlabel('days')
plt.ylabel('Cost of petrol')
#plt.show()

#gradient descent process

################################################
iteration=7000
alpha=0.005

################################################

# linear hypothesis function
# if written in this form theta should have n cols and 1 row
# there are n parameters
def h(theta,X):
    return np.dot(X,theta)

def computeCost(mytheta,X,y):
    """
    X is a matrix with n cols and m rows
    y is a matrix with m rows and 1 col
    """

    # m is y.size
    return float((1./(2*(y.size)))*np.dot((h(mytheta,X)-y).T,(h(mytheta,X)-y)))

initial_theta=np.zeros((x.shape[1],1))  # theta is a vector with n rows and 1 col
Old_cost_of_train=computeCost(initial_theta,x,y)
#print(Old_cost_of_train) 

# actual gradient descent minimizing routine


#(Note! This doesn't work unless we feature normalize! "overflow encountered in multiply")
def descendGradient(X,theta_start):
    theta=theta_start
    jvec=[]  # used to plot cost as functino of iteration
    theta_history=[]
    for meaninglessvariable in range(iteration):
        tmptheta=theta
        jvec.append(computeCost(theta,X,y))
        theta_history.append(list(theta[:,0])) # get the first column of theta 
        #simultaneously update theta
        for j in range(len(tmptheta)):
            tmptheta[j]=theta[j]-(alpha/m)*np.sum((h(theta,X)-y)*np.array(X[:,j]).reshape(m,1))
        theta=tmptheta
    return theta,theta_history,jvec

#initial_theta=np.zeros((X.shape[1],1))
initial_theta=np.zeros((x.shape[1],1))
theta,thetahistory,jvec=descendGradient(x,initial_theta)

# plot the convergence cost function

def plotConvergence(jvec):
    plt.figure(figsize=(10,6))
    plt.plot(range(len(jvec)),jvec,'bo') # set X and Y axis
    plt.grid(True)
    plt.title("Convergence of Cost Function")
    plt.xlabel("Iteration number")
    plt.ylabel("Cost Function")
    dummy=plt.xlim([-0.05*iteration,1.05*iteration]) #set the minimum and the maximum plotted on X axis设置X坐标最小值和最大值
    plt.show()
plotConvergence(jvec)

def myfit(xval):
    return theta[0]*xval[:,0]+theta[1]*xval[:,1]+theta[2]*xval[:,2]+theta[3]*xval[:,3]+theta[4]*xval[:,4]+theta[5]*xval[:,5]+theta[6]*xval[:,6]+theta[7]*xval[:,7]


plt.figure(figsize=(10,6))
plt.plot(X[:,0],y[:,0],'rx',markersize=10,label="Training Data")
temp=[]

plt.plot(X[:,0],myfit(x),'b-',label="Hypothesis: h(x) = %0.2f+%0.2fx+%0.2fx^2+%0.2fx^3+%0.2fx^4+%0.2fx^5+%0.2fx^6+%0.2fx^7"%(theta[0],theta[1],theta[2],theta[3],theta[4],theta[5],theta[6],theta[7]))
plt.grid(True)
plt.xlabel("day")
plt.ylabel("Cost of petrol")
plt.xlim(0,100)
plt.ylim(4000,7500)
plt.legend(loc=2)  #设置图例
plt.show()

print("Hypothesis: h(x) = %0.2f+%0.2fx+%0.2fx^2+%0.2fx^3+%0.2fx^4+%0.2fx^5+%0.2fx^6+%0.2fx^7"%(theta[0],theta[1],theta[2],theta[3],theta[4],theta[5],theta[6],theta[7]))

# Print the cost of train set
print("Old Cost of Train Set: %0.2f"%Old_cost_of_train) 
print("New Cost of Train Set: %0.2f"%computeCost(theta,x,y)) 

# Crossed Validation Set
datafile_cv = 'D:\TransportCompetition\Competition\结项论文\data_cv.txt'
cols_cv=np.loadtxt(datafile_cv,delimiter=',',usecols=(0,1),unpack=True) #使用‘，’作为分隔符，读取第一列和第二列，UNpack=True将分列读取
X_cv=np.transpose(np.array(cols_cv[:-1]))
y_cv=np.transpose(np.array(cols_cv[-1:]))
x_cv_2=X_cv**2 #每个元素都是X中的平方
x_cv_3=X_cv**3
x_cv_4=X_cv**4
x_cv_5=X_cv**5
x_cv=np.ones(X_cv.shape)
x_cv=np.append(x_cv,(X_cv-np.mean(X_cv[:,0]))/(np.std(X_cv[:,0])),axis=1)
x_cv=np.append(x_cv,(x_cv_2-np.mean(x_cv_2[:,0]))/(np.std(x_cv_2[:,0])),axis=1)
x_cv=np.append(x_cv,(x_cv_3-np.mean(x_cv_3[:,0]))/(np.std(x_cv_3[:,0])),axis=1)
x_cv=np.append(x_cv,(x_cv_4-np.mean(x_cv_4[:,0]))/(np.std(x_cv_4[:,0])),axis=1)
x_cv=np.append(x_cv,(x_cv_5-np.mean(x_cv_5[:,0]))/(np.std(x_cv_5[:,0])),axis=1)

print("Cost of CV Set: %0.2f"%computeCost(theta,x_cv,y_cv)) 

# Test Set

datafile_test = 'D:\TransportCompetition\Competition\结项论文\data_test.txt'
cols_test=np.loadtxt(datafile_test,delimiter=',',usecols=(0,1),unpack=True) #使用‘，’作为分隔符，读取第一列和第二列，UNpack=True将分列读取
X_test=np.transpose(np.array(cols_test[:-1]))
y_test=np.transpose(np.array(cols_test[-1:]))
x_test_2=X_test**2 #每个元素都是X中的平方
x_test_3=X_test**3
x_test_4=X_test**4
x_test_5=X_test**5
x_test=np.ones(X_test.shape)
x_test=np.append(x_test,(X_test-np.mean(X_test[:,0]))/(np.std(X_test[:,0])),axis=1)
x_test=np.append(x_test,(x_test_2-np.mean(x_test_2[:,0]))/(np.std(x_test_2[:,0])),axis=1)
x_test=np.append(x_test,(x_test_3-np.mean(x_test_3[:,0]))/(np.std(x_test_3[:,0])),axis=1)
x_test=np.append(x_test,(x_test_4-np.mean(x_test_4[:,0]))/(np.std(x_test_4[:,0])),axis=1)
x_test=np.append(x_test,(x_test_5-np.mean(x_test_5[:,0]))/(np.std(x_test_5[:,0])),axis=1)

print("Cost of Test Set: %0.2f"%computeCost(theta,x_test,y_test)) 



#指数滑动平均 
#方法的出处，来源文献，，将清楚方法
#设置训练集，交叉验证集，和测试集进行技术的验证
#