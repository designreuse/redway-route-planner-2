$( document ).ready(function() {
    console.log( "ready!" );
});

/////// Minimise button
  // When I click on .minimiser (without minimised)
  $( ".minimiser" ).click(function() {
    // change the minimiser icon (class edit)
    // shrink the input-box and its contents
    $( ".input-box" ).toggleClass( "minimised", 1000, "easeOutSine" );
    $( ".minimiser" ).toggleClass( "minimised-icon", 1000, "easeOutSine" );
    $( ".route-input" ).toggleClass( "hide", 500, "easeOutSine" );
    // and (becuase of toggle) When I click on .minimiser.minimised
      // hide the minimised icon
      // grow the input-box and its contents
  });

/////// Steps slideup/down
//When I click the arrow above the steps box
$( ".steps-box-button" ).click(function() {
  $( ".steps-box" ).slideToggle( "slow", function() {
    // Animation complete.
  });
  $( ".steps-box-icon" ).toggleClass( "rotated", 3000, "easeOutSine" );

});
