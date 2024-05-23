#include<iostream>
#include<math.h>
#include <vector>
#include <set>
#include <queue>

using namespace std;

bool solution(int x, int y, int z){
    /*
        if total capacity i.e. cap1+cap2< target, it is sure that one jug may be full but not = target
    */ 
    if(x + y < z){
        return false;
    }
    vector<int> directions = {x,-x,y,-y};
    set<int> measured;
    queue<int> q;
    measured.insert(0);
    q.push(0);
    while(!q.empty()){
        int now=q.front();
        q.pop();
        for(int direction: directions){
            int total=now+direction;
            if(total==z){
                return true;
            }
            if(total<0 || total >x+y){
                continue;
            }
            if(measured.find(total) == measured.end()){
                q.push(total);
                measured.insert(total);
            }
        }
    }
    return false;       
}


int main(){
    int capacity_j1 = 2;
    int capacity_j2 = 6;
    int measure = 4;
    std::cout<<solution(capacity_j1, capacity_j2, measure);
}