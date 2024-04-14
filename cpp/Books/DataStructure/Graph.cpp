//
// Created by rick on 2024/4/13.
//
#include <limits>
#include <optional>
#include <stack>
#include <vector>
#include <memory>
#include <queue>

enum class VStatus {
    UNDISCOVERED,
    DISCOVERED,
    VISITED
};
enum class EType {
    UNDETERMINED,
    TREE,
    CROSS,
    FORWARD,
    BACKWARD
};

template<typename Tv>
struct Vertex {
    Tv data;
    int inDegree;
    int outDegree;
    VStatus status;
    int dTime, fTime;
    int parent;
    int priority;

    explicit Vertex(const Tv &d) : data(d), inDegree(0), outDegree(0), status(VStatus::UNDISCOVERED),
                                   dTime(-1), fTime(-1), parent(-1), priority(std::numeric_limits<int>::max()) {}
};

enum class EStatus {
    UNDETERMINED,
    TREE,
    CROSS,
    FORWARD,
    BACKWARD
};

template<typename Te>
struct Edge {
    Te data;
    int weight;
    EStatus status;
    EType type;

    Edge(const Te &d, int w) : data(d), weight(w), status(EStatus::UNDETERMINED), type(EType::UNDETERMINED) {}
};

template<typename Tv, typename Te>
class Graph {
private:
    void reset() {
        for (auto i = 0; i < n; i++) {
            status(i) = VStatus::UNDISCOVERED;
            dTime(i) = fTime(i) = -1;
            parent(i) = -1;
            priority(i) = std::numeric_limits<int>::max();
            for (auto j = 0; j < n; j++) {
                if (exists(i, j)) type(i, j) = EStatus::UNDETERMINED;
            }
        }

    }

    void BFS(int v, int &clock) {
        std::queue<int> Q;
        status(v) = VStatus::DISCOVERED;
        Q.push(v);

        while (!Q.empty()) {
            auto vx = Q.front();
            Q.pop();
            dTime(vx) = ++clock;
            for (auto u = firstNbr(vx); u > -1; u = nextNbr(vx, u)) {
                if (status(u) == VStatus::UNDISCOVERED) {
                    status(u) = VStatus::DISCOVERED;
                    Q.push(u);
                    type(vx, u) = EType::TREE;
                    parent(u) = vx;
                } else {
                    type(vx, u) = EType::CROSS;
                }
            }
            status(vx) = VStatus::VISITED;
        }

    }

public:
    /**顶点总数*/
    int n;
    /**边数*/
    int e;

    /**插入顶点，返回编号 */
    virtual int insert(const Tv &) = 0;

    /** 删除顶点及其关联边，返回该顶点信息*/
    virtual Tv remove(int) = 0;

    /** 顶点v的数据 */
    virtual Tv &vertex(int) = 0;

    /** 顶点v的入度*/
    virtual int inDegree(int) = 0;

    /** 顶点v的出度*/
    virtual int outDegree(int) = 0;

    /** 顶点v的首个邻接顶点*/
    virtual int firstNbr(int) = 0;

    /**顶点v的（相对于顶点j的）下一邻接顶点 */
    virtual int nextNbr(int, int) = 0;

    /** 顶点v的状态 */
    virtual VStatus &status(int) = 0;

    virtual int &fTime(int) = 0;

    virtual int &dTime(int) = 0;

    virtual int &parent(int) = 0;

    virtual int &priority(int) = 0;

    virtual bool exists(int, int) = 0;

    virtual void insert(const Te &, int, int, int) = 0;

    virtual Te remove(int, int) = 0;

    virtual EType &type(int, int) = 0;

    virtual Te &edge(int, int) = 0;

    virtual int &weight(int, int) = 0;

    void bfs(int s) {
        reset();
        int clock = 0;
        int v = s;
        do {
            if (status(v) == VStatus::UNDISCOVERED)
                BFS(v, clock);
        } while (s != (v = (++v % n)));
    }

    void dfs(int);

    void bcc(int);

    std::stack<Tv> *tSort(int);

    void prim(int);

    void dijkstra(int);

    template<typename PU>
    void pfs(int, PU);
};

template<typename Tv, typename Te>
class GraphMatrix : public Graph<Tv, Te> {
private:
    std::vector<Vertex<Tv>> V;
    std::vector<std::vector<std::shared_ptr<Edge<Te>>>> E;
public:
    GraphMatrix() {
        this->n = this->e = 0;
    }

    Tv &vertex(int i) override { return V[i].data; }

    int inDegree(int i) override { return V[i].inDegree; }

    int outDegree(int i) override { return V[i].outDegree; }

    int firstNbr(int i) override { return this->nextNbr(i, this->n); }

    int nextNbr(int i, int j) override {
        while ((-1 < j) && (!exists(i, --j)));
        return j;
    }

    VStatus &status(int i) override { return V[i].status; }

    int &dTime(int i) override { return V[i].dTime; }

    int &fTime(int i) override { return V[i].fTime; }

    int &parent(int i) override { return V[i].parent; }

    int &priority(int i) override { return V[i].priority; }

    int insert(const Tv &vertex) override {
        for (int j = 0; j < this->n; j++) E[j].emplace_back(nullptr);
        this->n++;
        E.emplace_back(this->n, nullptr);
        V.emplace_back(vertex);
        return this->n;
    }

    bool exists(int i, int j) override {
        return (i >= 0) && (i < this->n) && (j >= 0) && (j < this->n) && E[i][j];
    }

    Tv remove(int i) override {
        for (int j = 0; j < this->n; j++) { // 所有出边
            if (this->exists(i, j)) {
                E[i][j].reset();
                V[j].inDegree--;
            }
        }

        // 删除第i行
        E.erase(E.begin() + i);
        this->n--;

        // 删除顶点i
        Tv vBak = vertex(i);
        V.erase(V.begin() + i);
        for (int j = 0; j < this->n; j++) {// 所有入边
            E[j].erase(E[j].begin() + i);
            V[j].outDegree--;
        }

        return vBak;
    }


    void insert(const Te &edge, int w, int i, int j) override {
        if (this->exists(i, j)) {
            return;
        }

        E[i][j] = std::make_shared<Edge<Te>>(edge, w);
        this->e++;
        V[i].outDegree++;
        V[j].inDegree++;

    }

    Te remove(int i, int j) override {
        Te eBak = edge(i, j);
        E[i][j].reset();
        this->e--;
        V[i].outDegree--;
        V[j].inDegree--;
        return eBak;
    }

    EType &type(int i, int j) override {
        return E[i][j]->type;
    }

    Te &edge(int i, int j) override {
        return E[i][j]->data;
    }

    int &weight(int i, int j) override {
        return E[i][j]->weight;
    }
};

int main() {
    GraphMatrix<int, int> gm;

}