puzzle = [
    ['J', 'S', 'O', 'L', 'U', 'T', 'I', 'S'],
    ['S', 'U', 'N', 'A', 'R', 'U', 'U', 'A'],
    ['N', 'R', 'P', 'T', 'U', 'N', 'E', 'T'],
    ['S', 'O', 'N', 'I', 'E', 'I', 'S', 'U'],
    ['R', 'C', 'E', 'V', 'T', 'R', 'E', 'R'],
    ['A', 'H', 'T', 'R', 'A', 'E', 'S', 'N'],
    ['M', 'M', 'E', 'R', 'C', 'U', 'R', 'Y']
]

puzzle_rows = len(puzzle)
puzzle_cols = len(puzzle[0])

row = 0
col = 0

directions = 8

key = "EARTH"

found = False
found_idx = [-1, -1]
count = 0

def check_directions(position, puzzle):
    print()

while(not found):
    while (not found) and (row < puzzle_rows):
        while (not found) and (col < puzzle_cols):
            curr_word = puzzle[row][col]    

            dir_state = 0
            while(dir_state < 8) and (not found):
                if(dir_state == 0):
                    boundary_length = row + 1
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row-finding_idx][col])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]
               
                elif(dir_state == 1):
                    boundary_length = row + 1
                    x_boundary = puzzle_cols - col 
                    if (x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row-finding_idx][col+finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]
                
                elif(dir_state == 2):
                    boundary_length = puzzle_cols - col 
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row][col+finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]
                
                elif(dir_state == 3):
                    boundary_length = puzzle_cols - col 
                    y_boundary = puzzle_rows - row
                    if(y_boundary < boundary_length):
                        boundary_length = y_boundary
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row+finding_idx][col+finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]
                
                elif(dir_state == 4):
                    boundary_length = puzzle_rows - row
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row+finding_idx][col])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]
                
                elif(dir_state == 5):
                    boundary_length = puzzle_rows - row
                    x_boundary = col + 1
                    if(x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row+finding_idx][col-finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]

                elif(dir_state == 6):
                    boundary_length = col + 1
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row][col-finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]

                elif(dir_state == 7):
                    boundary_length = row + 1
                    x_boundary = col + 1
                    if(x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(key)) and (key[finding_idx] == puzzle[row-finding_idx][col-finding_idx])):
                            finding_idx += 1
                        if (finding_idx == len(key)):
                            found = True
                            found_idx = [row, col]

                dir_state += 1
                print(dir_state)
            col += 1
            print(f"({row}, {col})")
        row += 1

print(f"Hasil ditemukan: {found}, dengan posisi ({found_idx[0]}, {found_idx[1]})")

    # for row in range (puzzle_rows):
    #     for col in range (puzzle_cols):
    #         # print(count+1)
    #         # count += 1
    #         curr_word = puzzle[row][col]
