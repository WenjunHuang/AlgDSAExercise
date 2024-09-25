package structure.bridge

// bridge
trait DrawingTool:
  def drawCircle(x: Double, y: Double, radius: Double): Unit
  def drawRectangle(x: Double, y: Double, width: Double, height: Double): Unit

sealed abstract class Shape(protected val drawingTool: DrawingTool):
  def draw(): Unit

class Circle(drawingTool: DrawingTool, private val x: Double, private val y: Double, private val radius: Double) extends Shape(drawingTool):
  override def draw(): Unit = drawingTool.drawCircle(x, y, radius)

class Rectangle(drawingTool: DrawingTool, private val x: Double, private val y: Double, private val width: Double, private val height: Double) extends Shape(drawingTool):
  override def draw(): Unit = drawingTool.drawRectangle(x, y, width, height)

class OpenGLDrawingTool extends DrawingTool:
  override def drawCircle(x: Double, y: Double, radius: Double): Unit =
    println(s"OpenGL drawCircle at ($x, $y) with radius $radius")
  override def drawRectangle(x: Double, y: Double, width: Double, height: Double): Unit =
    println(s"OpenGL drawRectangle at ($x, $y) with width $width and height $height")

class VulkanDrawingTool extends DrawingTool:
  override def drawCircle(x: Double, y: Double, radius: Double): Unit =
    println(s"Vulkan drawCircle at ($x, $y) with radius $radius")
  override def drawRectangle(x: Double, y: Double, width: Double, height: Double): Unit =
    println(s"Vulkan drawRectangle at ($x, $y) with width $width and height $height")

// abstract factory

trait ShapeFactory:
  def createCircle(x: Double, y: Double, radius: Double): Shape
  def createRectangle(x: Double, y: Double, width: Double, height: Double): Shape

class OpenGLShapeFactory extends ShapeFactory:
  override def createCircle(x: Double, y: Double, radius: Double): Shape =
    new Circle(new OpenGLDrawingTool, x, y, radius)
  override def createRectangle(x: Double, y: Double, width: Double, height: Double): Shape =
    new Rectangle(new OpenGLDrawingTool, x, y, width, height)

class VulkanShapeFactory extends ShapeFactory:
  override def createCircle(x: Double, y: Double, radius: Double): Shape = new Circle(new VulkanDrawingTool, x, y, radius)

  override def createRectangle(x: Double, y: Double, width: Double, height: Double): Shape = new Rectangle(new VulkanDrawingTool, x, y, width, height)

@main
def runBridgeWithAbstractFactory: Unit =
  val openGLShapeFactory: ShapeFactory = new OpenGLShapeFactory
  val vulkanShapeFactory: ShapeFactory = new VulkanShapeFactory

  val circle1: Shape = openGLShapeFactory.createCircle(0, 0, 10)
  val circle2: Shape = vulkanShapeFactory.createCircle(0, 0, 10)
  circle1.draw()
  circle2.draw()

  val rectangle1: Shape = openGLShapeFactory.createRectangle(0, 0, 10, 20)
  val rectangle2: Shape = vulkanShapeFactory.createRectangle(0, 0, 10, 20)
  rectangle1.draw()
  rectangle2.draw()
