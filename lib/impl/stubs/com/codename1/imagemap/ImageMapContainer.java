package com.codename1.imagemap;


/**
 *  A UI component for displaying an image map.  I.e. An image with clickable "hot" areas.
 *  
 *  Note that this container can be resized, and the image and clickable areas will be resized 
 *  accordingly.
 *  @author shannah
 */
public class ImageMapContainer extends com.codename1.ui.Container implements com.codename1.ui.events.ActionSource {

	/**
	 *  Creates a new Image map container.
	 *  @param image The image to display.
	 *  @param width The width of the image map.  ClickableAreas are sized relative to this.
	 *  @param height The height of the image map. ClickableAreas are sized relative to this.
	 */
	public ImageMapContainer(com.codename1.ui.Image image, int width, int height) {
	}

	@java.lang.Override
	public void paint(com.codename1.ui.Graphics g) {
	}

	/**
	 *  Adds a clickable area to the image map.
	 *  @param type THe type of area.  
	 *  @param rect The bounds of the clicable area, relative to the map size (as defined in the ImageMapContainer constructor).
	 *  @param l Handle when the area is clicked.
	 */
	public void addClickableArea(ImageMapContainer.ClickableAreaType type, com.codename1.ui.geom.Rectangle rect, com.codename1.ui.events.ActionListener l) {
	}

	/**
	 *  Add one or more clickable areas to the image map.
	 *  @param areas 
	 */
	public void addClickableAreas(ImageMapContainer.ClickableArea[] areas) {
	}

	@java.lang.Override
	protected void initComponent() {
	}

	/**
	 *  {@inheritDoc }
	 *  @param l 
	 */
	@java.lang.Override
	public void addActionListener(com.codename1.ui.events.ActionListener l) {
	}

	/**
	 *  {@inheritDoc }
	 *  @param l 
	 */
	@java.lang.Override
	public void removeActionListener(com.codename1.ui.events.ActionListener l) {
	}

	/**
	 *  A clickable area on an image map.
	 */
	public class ClickableArea {


		/**
		 *  Create a new clickable area.
		 *  @param type THe type of area.
		 *  @param bounds The bounds of the area, relative to the image map size (as defined in its constructor).
		 *  @param clickListener Listener called when area is clicked.
		 */
		public ClickableArea(ImageMapContainer.ClickableAreaType type, com.codename1.ui.geom.Rectangle bounds, com.codename1.ui.events.ActionListener clickListener) {
		}
	}

	/**
	 *  Enum with clicable area types.
	 */
	public static final class ClickableAreaType {


		/**
		 *  A rectanglular clickable area.
		 */
		public static final ImageMapContainer.ClickableAreaType Rect;

		/**
		 *  Oval clickable area.
		 */
		public static final ImageMapContainer.ClickableAreaType Oval;

		public static ImageMapContainer.ClickableAreaType[] values() {
		}

		public static ImageMapContainer.ClickableAreaType valueOf(String name) {
		}
	}
}
