import {TreeNode} from "./util.ts";
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

const NULL = "#"

class SerializerBuilder {
    private sb: string[] = []

    constructor(private treeNode: TreeNode) {
    }

    serialize(): string {
        this.serializeBuilder(this.treeNode)
        return this.sb.join(",")
    }

    serializeBuilder(root: TreeNode | null) {
        if (root == null) {
            this.sb.push(`${NULL}`)
        } else {
            this.sb.push(`${root.val}`)
            this.serializeBuilder(root.left)
            this.serializeBuilder(root.right)
        }
    }
}


/*
 * Encodes a tree to a single string.
 */
function serialize(root: TreeNode | null): string {
    if (root == null) return "#"
    const ser = new SerializerBuilder(root)
    return ser.serialize()
}

class Deserializer {
    nodes: string[]

    constructor(encoded: string) {
        this.nodes = encoded.split(",");
    }

    deserialize(): TreeNode | null {
        if (this.nodes.length == 0) return null

        const first = this.nodes[0]
        this.nodes.splice(0,1)

        if (first === NULL) return null

        const root = new TreeNode(parseInt(first))
        root.left = this.deserialize()
        root.right = this.deserialize()

        return root;
    }
}


/*
 * Decodes your encoded data to tree.
 */
function deserialize(data: string): TreeNode | null {
    const des = new Deserializer(data)
    return des.deserialize()
}


/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */
//leetcode submit region end(Prohibit modification and deletion)

const input = new TreeNode(1, new TreeNode(2), new TreeNode(3))
const r = serialize(null)
const n = deserialize(r)
console.log(n)