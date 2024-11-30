import sys

def search(array):
    count1=0
    count2=0

    # W first
    for i in range(8):
        for j in range(8):
            if ((i % 2 ==0) and (j % 2== 0)) or ((i % 2 == 1) and (j % 2 ==1)): # 행렬이 각각 짝수거나 홀수
                if(array[i][j] != 'W'):
                    count1 +=1

            elif((i % 2 ==1) and (j % 2== 0) or (i % 2 == 0) and (j % 2 ==1)):
                if(array[i][j] != 'B'):
                    count1 +=1

    # B first
    for i in range(8):
        for j in range(8):
            if((i % 2 ==0 )and( j % 2== 0)) or ((i % 2 == 1) and (j % 2 ==1)): # 행렬이 각각 짝수거나 홀수
               if( array[i][j] != 'B'):
                    count2 +=1

            elif ((i % 2 ==1) and (j % 2== 0) or (i % 2 == 0 )and( j % 2 ==1)):
                if(array[i][j] != 'W'):
                    count2 +=1
    
    return min(count1, count2)


N, M= input().split()
N=int(N)
M=int(M)

arr = []

for id in range(N):
    arr.append([i for i in sys.stdin.readline()][:-1])


min_count= 1000000
for i in range(N -7):
    for j in range(M-7):
        new_arr=[width[j:j+8] for width in arr[i:i+8]]
        result= search(new_arr)
        min_count= min(min_count, result)

print(min_count)